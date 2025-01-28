# ---- Etap 1: Budowa aplikacji (build stage) ----
# Brak oficjalnego obrazu "gradle:...-jdk21", więc bazujemy na openjdk:21-slim i instalujemy Gradle ręcznie.
FROM openjdk:21-slim AS build

# Zainstaluj podstawowe narzędzia (curl, unzip) aby móc pobrać i zainstalować Gradle (np. przez SDKMAN)
RUN apt-get update && apt-get install -y curl unzip \
    && rm -rf /var/lib/apt/lists/*

# Instalacja SDKMAN, przez który doinstalujemy Gradle
RUN curl -s "https://get.sdkman.io" | bash

# Zmienne środowiskowe dla SDKMAN
ENV SDKMAN_DIR="/root/.sdkman" \
    PATH="/root/.sdkman/bin:/root/.sdkman/candidates/gradle/current/bin:${PATH}"

# Instalacja Gradle (przykładowo wersja 8.2; w razie potrzeby zmień na inną)
RUN bash -c "source $SDKMAN_DIR/bin/sdkman-init.sh && sdk install gradle 8.2"

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy pliki projektu (w tym build.gradle, gradle.properties, src, etc.)
COPY . .

# Budujemy aplikację (tworzymy plik .jar)
RUN bash -c "source $SDKMAN_DIR/bin/sdkman-init.sh && gradle bootJar --no-daemon"

# ---- Etap 2: Obraz produkcyjny (runtime stage) ----
FROM openjdk:21-slim

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy gotowy plik JAR z etapu build
COPY --from=build /app/build/libs/*.jar app.jar

# Otwieramy port aplikacji (domyślnie 8080)
EXPOSE 8080

# Uruchamiamy aplikację
ENTRYPOINT ["java", "-jar", "app.jar"]
