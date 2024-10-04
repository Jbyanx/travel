correr la imagen de postgres:

docker run --name postgres-pweb \
  -e POSTGRES_PASSWORD=sebaj \
  -e POSTGRES_USER=jabes \
  -e POSTGRES_DB=travel_db \
  -p 15432:5432 \
  -d postgres

Insertar datos:
1.Aerolinea: no depende de otra entidad
2.Aeropuerto: no depende de otra entidad
3.Cliente: no depende de otra entidad
4.Escala: necesita el id de Aeropuerto y de vuelo
5.Reserva: necesita un id de cliente y uno de vuelo
6.Vuelo: necesita el id de Aerolinea y de Aeropuerto de origen y destino