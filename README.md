# TurkishSentiNet

Turkish SentiNet (HisNet) is a Turkish polarity dictionary made up of 80.000 synsets (sets of synonymous words and expressions). Polarity dictionaries are corpora in which the entries are categorized into three groups: positive, neutral and negative. This resource is meant to be used for sentiment analysis, in which a text can be evaluated in terms of its judgements of positive or negative meaning. 

For Developers
============
You can also see either [Python](https://github.com/olcaytaner/TurkishSentiNet-Py) 
or [C++](https://github.com/olcaytaner/TurkishSentiNet-CPP) repository.
## Requirements

* [Java Development Kit 8 or higher](#java), Open JDK or Oracle JDK
* [Maven](#maven)
* [Git](#git)

### Java 

To check if you have a compatible version of Java installed, use the following command:

    java -version
    
If you don't have a compatible version, you can download either [Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or [OpenJDK](https://openjdk.java.net/install/)    

### Maven
To check if you have Maven installed, use the following command:

    mvn --version
    
To install Maven, you can follow the instructions [here](https://maven.apache.org/install.html).      

### Git

Install the [latest version of Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

## Download Code

In order to work on code, create a fork from GitHub page. 
Use Git for cloning the code to your local or below line for Ubuntu:

	git clone <your-fork-git-link>

A directory called SentiNet.SentiNet will be created. Or you can use below link for exploring the code:

	git clone https://github.com/olcaytaner/SentiNet.SentiNet.git

## Open project with IntelliJ IDEA

Steps for opening the cloned project:

* Start IDE
* Select **File | Open** from main menu
* Choose `SentiNet.SentiNet/pom.xml` file
* Select open as project option
* Couple of seconds, dependencies with Maven will be downloaded. 


## Compile

**From IDE**

After being done with the downloading and Maven indexing, select **Build Project** option from **Build** menu. After compilation process, user can run SentiNet.SentiNet.

**From Console**

Go to `SentiNet.SentiNet` directory and compile with 

     mvn compile 

## Generating jar files

**From IDE**

Use `package` of 'Lifecycle' from maven window on the right and from `SentiNet.SentiNet` root module.

**From Console**

Use below line to generate jar file:

     mvn install

## Maven Usage

        <dependency>
            <groupId>io.github.starlangsoftware</groupId>
            <artifactId>SentiNet</artifactId>
            <version>1.0.5</version>
        </dependency>

------------------------------------------------

Detailed Description
============
+ [SentiNet](#sentinet)
+ [SentiSynSet](#sentisynset)

## SentiNet

Duygu sözlüğünü yüklemek için

	a = SentiNet()

Belirli bir alana ait duygu sözlüğünü yüklemek için

	SentiNet(String fileName)
	a = SentiNet("dosya.txt");

Belirli bir synsete ait duygu synsetini elde etmek için

	SentiSynSet getSentiSynSet(String id)

## SentiSynSet

Bir SentiSynset elimizdeyken onun pozitif skorunu

	double getPositiveScore()

negatif skorunu

	double getNegativeScore()

polaritysini

	PolarityType getPolarity()
