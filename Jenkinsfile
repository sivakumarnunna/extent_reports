pipeline {
    agent any
 
 tools { 
      maven 'Maven3.9.6' 
      jdk 'jdk17' 
    }
    stage('reading properties from properties file') {
    steps {
        // Use a script block to do custom scripting
        script {
            def props = readProperties file: 'config.properties' 
            //env.Username = props.Username
        }
        echo "The username  is ${props.browser}"
    }
}


    stages {
        stage('Test') {
            steps {
sh 'mvn compile'            }
 
            post {                
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                   publishHTML([
                       allowMissing: false, 
                              alwaysLinkToLastBuild: false, 
                              keepAll: false, 
                              reportDir: 'Reports', 
                              reportFiles: 'Spark.html', 
                              reportName: 'ExtentReport', 
                              reportTitles: '', 
                              useWrapperFileDirectly: true])

                    emailext body: 'Test mesage Test mesage', recipientProviders: [developers()], subject: 'Test message', to: 'nsk.arch@gmail.com'
                }
            }
        }
    }
}
