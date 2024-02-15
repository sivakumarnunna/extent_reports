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
        always {
          // publish html
          publishHTML target: [
              allowMissing: false,
              alwaysLinkToLastBuild: true,
              keepAll: true,
              reportDir: 'Reports',
              reportFiles: 'Spark.html',
              reportName: 'SparkReport',
             reportTitles: 'The Report']
            
        }
      }
        
    
    
    }
    
    
 }
 }
