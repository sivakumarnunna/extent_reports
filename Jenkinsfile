pipeline {
    agent any
 
 tools { 
      maven 'Maven3.9.6' 
      jdk 'jdk17' 
    }
   

    stages {
        stage('Test') {
            steps {
                script{
                 def props = readProperties file: 'config.properties' 
                    $props.browser = ${browser}
                  echo "The username  is $props.browser"
                }
sh 'mvn compile' 
            
            }
 
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
