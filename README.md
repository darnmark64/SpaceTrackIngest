# SpaceTrackIngest
This is a simple Spring Boot JAVA application that pulls data from a REST endpoint and posts the data to another REST endpoint.

This application queries space-track.org for objects of type *PAYLOAD* and uses the JSON to create Java POJOs, which are translated 
into CODEX space vehicle POJOS. The CODEX objects are translated into JSON and posted to a local CODEX REST endpoint to insert the
data into the CODEX database. 
