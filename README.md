correr la imagen de postgres:

docker run --name postgres-pweb \
  -e POSTGRES_PASSWORD=sebaj \
  -e POSTGRES_USER=jabes \
  -e POSTGRES_DB=travel_db \
  -p 15432:5432 \
  -d postgres
