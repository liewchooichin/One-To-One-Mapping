# Tutorial on one-to-one mapping

I am using the tutorial to learn more about OneToOne mapping:

[One-To-One Mapping](https://springjava.com/spring-data-jpa/one-to-one-bidirectional-mapping-in-spring-boot-jpa/)

[Spring Data JPA Find By Contains](https://springjava.com/spring-data-jpa/find-by-contains/)


# Sample Output

## Create a new record

`POST localhost:8080\api\save`

```
{
  "employeeModelName": "name two",
  "employeeModelEmailId": "two@email.com",
  "employeeModelMobile": "1002",
  "employeeModelDesign": "design two",
  "employeeModelStreet": "street two",
  "employeeModelCity": "city two",
  "employeeModelState": "state two"
  }
```

## Get the employees

`GET localhost:8080\api\employees`

```
{
  "status": 1,
  "data": [
    {
      "employeeModelId": 1,
      "employeeModelName": "name one",
      "employeeModelEmailId": "one@email.com",
      "employeeModelMobile": "1001",
      "employeeModelDesign": "design one",
      "employeeModelStreet": "street one",
      "employeeModelCity": "city one",
      "employeeModelState": "state one"
    },
    {
      "employeeModelId": 2,
      "employeeModelName": "name two",
      "employeeModelEmailId": "two@email.com",
      "employeeModelMobile": "1002",
      "employeeModelDesign": "design two",
      "employeeModelStreet": "street two",
      "employeeModelCity": "city two",
      "employeeModelState": "state two"
    }
  ]
}
```

## Get the address list

`GET localhost:8080/api/address-list`

```
{
  "status": 1,
  "data": [
    {
      "employeeModelId": 1,
      "employeeModelName": "name one",
      "employeeModelEmailId": "one@email.com",
      "employeeModelMobile": "1001",
      "employeeModelDesign": "design one",
      "employeeModelStreet": "street one",
      "employeeModelCity": "city one",
      "employeeModelState": "state one"
    },
    {
      "employeeModelId": 2,
      "employeeModelName": "name two",
      "employeeModelEmailId": "two@email.com",
      "employeeModelMobile": "1002",
      "employeeModelDesign": "design two",
      "employeeModelStreet": "street two",
      "employeeModelCity": "city two",
      "employeeModelState": "state two"
    }
  ]
}
```