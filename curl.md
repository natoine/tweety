curl -H "Accept: application/json" http://localhost:9000/wall
curl -H "Accept: application/rdf+xml" http://localhost:9000/wall
curl -X POST --data "comment=blablabla&username=toto" http://localhost:9000/newtweet
