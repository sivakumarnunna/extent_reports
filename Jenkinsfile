pipeline {
    agent any
 
 tools { 
      maven 'Maven3.9.6' 
      jdk 'jdk17' 
    }


stage(“reading properties from properties file”) {
    steps {
        // Use a script block to do custom scripting
        script {
            def props = readProperties file: 'extravars.properties' 
            env.Username = props.Username
        }
        echo "The username  is $Username"
    }
}
    stages {
        stage('Test') {
            steps {
sh 'mvn clean test'            }
 
            post {                
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                  
                }
            }
        }
