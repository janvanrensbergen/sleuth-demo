# sleuth-client

Simpel web app met form post: http://localhost:8484/

# sleuth-rest

Simpel rest controller

curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Eric","name":"Cartman"}' http://localhost:8181/person

# sleuth-some-service

Rest controllerke die dingskes binnenkrijgt van client of rest-api.
Zal dan een message op ne queue zetten.

Dus ff lokaal ne activemq draaien.

brew install activemq
activemq console

# sleuth-some-other-service
Luistert naar de berichten die andere service op queue hft gezet.

# sleuth-zipkin

Om lokaal zipkin te draaien
java -jar zikpik.jar