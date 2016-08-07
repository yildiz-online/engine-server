# Yildiz-Engine engine-server

This is the official repository of the Engine Server library, part of the Yildiz-Engine project.
The engine server library is the meant to build a solid server accepting thousand of connexions and processing them.

## Features

* Fast network.
* Persitent layer.
* ...

## Requirements

To build this module, you will need a java 8 JDK and Maven 3.

## Coding Style and other information

Project website:
http://www.yildiz-games.be

Issue tracker:
https://yildiz.atlassian.net

Wiki:
https://yildiz.atlassian.net/wiki

Quality report:
https://sonarqube.com/overview?id=be.yildiz-games:engine-server

## License

All source code files are licensed under the permissive MIT license
(http://opensource.org/licenses/MIT) unless marked differently in a particular folder/file.

## Build instructions

Go to your root directory, where you POM file is located.

Then invoke maven

	mvn clean install

This will compile the source code, then run the unit tests, and finally build a jar file.

If you need to regenerate the database classes, invoke maven like this:

        mvn clean install -P generate-model -Ddb.user=username -Ddb.password=password -Ddb.url=connection_url -Ddb.name=db_name

## Usage

To use the snapshot versions, please add the following repository
https://oss.sonatype.org/content/repositories/snapshots/

Released version are retrieved from maven central.

In your maven project, add the dependency

```xml
<dependency>
    <groupId>be.yildiz-games</groupId>
    <artifactId>engine-server</artifactId>
    <version>1.0.0-0-SNAPSHOT</version>
</dependency>
```

## Contact
Owner of this repository: Grégory Van den Borre