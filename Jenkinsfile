pipeline {

agent any
 
 tools { 
      maven 'Maven3.9.6' 
      jdk 'jdk17' 
    }
    
    
    
    stages {
    
     stage('Test') {
            steps {
sh 'mvn clean test'        

    }
 
   post {
        success {
          // publish html
          publishHTML target: [
              allowMissing: false,
              alwaysLinkToLastBuild: false,
              keepAll: true,
              reportDir: 'Reports',
              reportFiles: 'Spark.html',
              reportName: 'Spark Report'
            ]
        }
      }
        
    
    
    }
    
    
 }
 }
