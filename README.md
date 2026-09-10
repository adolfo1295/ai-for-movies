# AIForMovies

AIForMovies es una aplicación Android pequeña creada como proyecto público de
aprendizaje sobre el uso de agentes de IA en el desarrollo Android.

## Requisitos

- Android Studio con el SDK de Android instalado.
- Una cuenta gratuita de [TMDB](https://www.themoviedb.org/).
- Una API Key de TMDB para autenticación v3.

## Configurar tu API Key de TMDB

1. Inicia sesión en TMDB.
2. Abre la configuración de tu cuenta y entra en la sección **API**.
3. Solicita o copia tu **API Key (v3 auth)**. No uses el **API Read Access Token**.
4. Abre el archivo `local.properties`, ubicado en la raíz del proyecto. Android
   Studio lo crea automáticamente al abrir el proyecto.
5. Agrega tu clave sin comillas:

   ```properties
   TMDB_API_KEY=TU_API_KEY
   ```

6. Sincroniza Gradle y ejecuta la
   configuración `app` en un emulador o dispositivo.

`local.properties` está ignorado por Git. No publiques, grabes ni agregues tu
clave al repositorio. Cada persona que clone el proyecto debe utilizar su propia
API Key.

## Solución de problemas

Si la aplicación muestra el mensaje
`TMDB_API_KEY is missing. Add it to local.properties.`, comprueba que:

- El archivo se llama exactamente `local.properties`.
- Está ubicado en la raíz del proyecto, junto a `settings.gradle.kts`.
- La propiedad se llama exactamente `TMDB_API_KEY`.
- Sincronizaste Gradle o reconstruiste la aplicación después de agregarla.

## Checkpoints de los episodios

El proyecto utiliza tags de Git como `ep01-start` y `ep01-end` para que cada
video pueda enlazar la versión exacta que se muestra.
