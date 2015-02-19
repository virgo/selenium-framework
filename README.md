# selenium-framework
A Selenium 2.0 framework for web automation

## Build

[https://travis-ci.org/virgo/selenium-framework](https://travis-ci.org/virgo/selenium-framework)

![Build Status](https://travis-ci.org/virgo/selenium-framework.svg?branch=master "Build")


## Usage

### Maven
Add these lines to your project's pom.xml

	<repositories>
		<repository>
			<id>sonatype-nexus-snapshots</id>
			<name>Sonatype Nexus snapshot repository</name>
			<url>https://oss.sonatype.org/content/repositories/snapshots</url>
		</repository>
	</repositories>
	
	<dependencies>
		<dependency>
			<groupId>hu.virgo</groupId>
			<artifactId>selenium-framework</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	</dependencies>