REGISTER
POST:localhost:9090/user/register
{
    "username" : "admin1",
    "password" : "admin1",
    "role" : "admin",
    "email" : "admin1@gmail.com"
}

LOGIN
POST:localhost:9090/user/login
{
    "username" : "admin1",
    "password" : "admin1"
}