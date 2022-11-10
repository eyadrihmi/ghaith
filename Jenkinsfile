pipeline {
    agent any

    stages {
        
                stage('git') {
            steps {
            
                git branch: 'Ghaith', url: 'https://github.com/GhaithBh/Devops.git',
                credentialsId:"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";
                
            }
}
        
       stage('MVN Package'){
            steps {
                sh """mvn -version  """
                sh """java -version """
               sh """mvn package -e """
            }
        }
        
      stage("MVN Compile"){
            steps {
                sh """mvn compile -e """
                
            }
        }
      stage("SONARQUBE"){
            steps {
                sh """mvn sonar:sonar """
                
            }
        }
        stage("Junit/Mockito"){
            steps {
                sh """mvn test """
                
            }
        }
        stage('Nexus'){
            steps{
                sh """mvn deploy """
            }
        }
           stage("MVN Install"){
            steps {
                sh """mvn install """
                
            }
        }
        stage("MVN Clean"){
            steps {
                sh """mvn clean -e """
                
            }
        }

    }
}
