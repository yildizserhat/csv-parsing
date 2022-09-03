
# CSV parsing

The goal is to create an application that allows uploading and getting a csv file.
The sample csv file can be found in resource folder

Note: The field code is unique
## API Reference

#### Get All Record

```http
  GET /v1/records
```

#### Delete All Record

```http
  DELETE /v1/records
```


#### Get Record by Code

```http
  GET /v1/records/{code}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `code` | `string` | **Required**.  |

#### Upload CSV File

```http
  POST /v1/records/upload
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `file`      | `Multipart File` | **Required**. CSV File|

#### Database 

```http
  localhost:8080/h2-console 
  username: sa
  password: sa
```

#### Documentation 

```http
  localhost:8080/documentation 
```



## Tech Stack

**Technologies:** Java 17, Spring Boot, h2 in-memory database, Docker

