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
 
   publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, 
   reportDir: 'Reports', 
   reportFiles: 'Spark.html', 
   reportName: 'Spark Html report',
    reportTitles: '',
     useWrapperFileDirectly: true])
       
    
        }
        
        
    
    
    }
    
    
 }
