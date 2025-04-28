# Todo App

This is a simple CRUD Todo application built with Java, running on Tomcat 8 with HTTPS enabled, and Azure SQL Database connection.

---

## Project Structure
```
pom.xml
schema.sql
src
└── main
    ├── java
    │   └── com
    │       └── example
    │           ├── dao
    │           │   └── TodoDao.java
    │           ├── model
    │           │   └── Todo.java
    │           ├── servlet
    │           │   └── TodoServlet.java
    │           └── util
    │               └── DatabaseUtil.java
    └── webapp
        ├── index.jsp
        └── WEB-INF
            └── web.xml
```
---

## Requirements

- Ubuntu 22.04+
- OpenJDK 11
- Maven
- Git
- Apache Tomcat 8.5.96
- Azure SQL Database
- SSL Certificate (`.pfx` format)

---

## Installation Guide

Follow these steps to deploy the application from scratch on a new VM.

### 1. Update the System
```bash
sudo apt update && sudo apt upgrade -y
```
### 2. Install Java (OpenJDK 11)
```bash
sudo apt install openjdk-11-jdk -y
```
### 3. Install Maven
```bash
sudo apt install maven -y 
```
### 4. Install Git
```bash
sudo apt install git -y
```
### 5. Install Tomcat 8.5.96
```bash
cd /opt
sudo wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.96/bin/apache-tomcat-8.5.96.tar.gz
sudo tar -xzf apache-tomcat-8.5.96.tar.gz
sudo mv apache-tomcat-8.5.96 tomcat8
sudo rm apache-tomcat-8.5.96.tar.gz
sudo bash -c 'chmod +x /opt/tomcat8/bin/*.sh'
```
### 6. Install MSSQL Tools
```bash
curl https://packages.microsoft.com/keys/microsoft.asc | sudo apt-key add -
curl https://packages.microsoft.com/config/ubuntu/22.04/prod.list | sudo tee /etc/apt/sources.list.d/mssql-release.list
sudo apt update
sudo ACCEPT_EULA=Y apt install -y msodbcsql18 mssql-tools18
echo export PATH="$PATH:/opt/mssql-tools18/bin" >> ~/.bashrc source ~/.bashrc
```
### 7. Set up Azure SQL Database
```bash
sqlcmd -S <your-server>.database.windows.net -U <your-username> -P <your-password> -d <your-database> -i schema.sql
```
> Replace `<your-server>`, `<your-username>`, `<your-password>`, `<your-database>` accordingly.

---

## Build and Deploy the App

### 1. Build the WAR package
```bash
cd /path/to/todo-app
mvn clean package
```
### 2. Deploy to Tomcat
```bash
sudo rm -rf /opt/tomcat8/webapps/todo-app*
sudo cp target/todo-app.war /opt/tomcat8/webapps/
sudo /opt/tomcat8/bin/shutdown.sh 
sleep 5
sudo /opt/tomcat8/bin/startup.sh` 
```
---

## Enable HTTPS (SSL)

### 1. Place your `.pfx` certificate
```bash
sudo mkdir -p /opt/tomcat8/certs
sudo cp /path/to/your/certificate.pfx /opt/tomcat8/certs/
```
### 2. Update Tomcat Configuration

Edit `/opt/tomcat8/conf/server.xml` and add the following under the `<Service name="Catalina">` section:
```html
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
           maxThreads="200" SSLEnabled="true" scheme="https" secure="true">
    <SSLHostConfig>
        <Certificate certificateKeystoreFile="/opt/tomcat8/certs/certificate.pfx"
                     certificateKeystorePassword="" />
    </SSLHostConfig>
</Connector>
```
Then restart Tomcat:
```bash
sudo /opt/tomcat8/bin/shutdown.sh 
sleep 5
sudo /opt/tomcat8/bin/startup.sh 
```

---

## Access the Application

-   HTTP: `http://<server-ip>:8080/todo-app/todos`
    
-   HTTPS: `https://<server-ip>:8443/todo-app/todos`
