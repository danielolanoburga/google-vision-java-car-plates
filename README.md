# Proyecto IA - Reconocimiento de placas de carros con Google Vision
Este repositorio contiene el proyecto correspondiente al curso de Inteligencia Artificial de UPC, el cual comprende una aplicación web Java que carga imagenes de placas de carro y por medio del API de Google Vision se procesa para reconocer el texto de la placa y el reconocimiento del modelo del carro.

### Integrantes:

  - Daniel Olano
  - Delia Quispe
  - Jordy Mamani

![image1](https://user-images.githubusercontent.com/12433354/86022600-36267100-b9f0-11ea-9493-d49b3193f00c.png)
![image2](https://user-images.githubusercontent.com/12433354/86022603-37579e00-b9f0-11ea-86e2-bd0918e4421a.png)
![image3 1](https://user-images.githubusercontent.com/12433354/86022605-37579e00-b9f0-11ea-92f2-30df1731f727.png)
![image3](https://user-images.githubusercontent.com/12433354/86022607-37f03480-b9f0-11ea-9424-6080cd9cfcaf.png)
![image4](https://user-images.githubusercontent.com/12433354/86022609-37f03480-b9f0-11ea-9761-57ac199516d9.png)
![image5](https://user-images.githubusercontent.com/12433354/86032263-a6d38a80-b9fc-11ea-9ecf-437d4074c37f.png)

### Development

First Tab:
1. CREAR PROYECTO EN GOOGLE CLOUD PLATFORM (GCP)
```sh
$ google-vision-java-car-plates
```

2. ACTIVAR EL API POR MEDIO DE LA CONSOLA DE GCP:
```sh
$ gcloud auth list
$ gcloud config list project
$ gcloud services enable vision.googleapis.com
```

3. CREAR CUENTA DE SERVICIO EN GCP
```sh
$ service-google-vision-java
```
4. CREAR KEY FILE Y DESCARGAR EN FORMATO JSON. CONFIGURARLO COMO VARIABLE DE ENTORNO.
5. ACTIVAR BILLING PARA USAR API DE GOOGLE
3. CREAR CUENTA DE SERVICIO EN GCP
https://console.developers.google.com/billing/enable?project=XXXXXXX
