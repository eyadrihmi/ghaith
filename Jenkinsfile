pipeline {
    agent any

    stages {
        
                stage('git') {
            steps {
            
                git branch: 'Ghaith', url: 'https://github.com/GhaithBh/Devops.git',
                credentialsId:"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";
                
            }
}
     

        stage('cleanig the project') {
            steps{
                sh 'mvn clean'
            }

        }
       stage ('artifact construction') {
            steps{
                sh '''
                docker restart mysql || true
                mvn  package
                '''
            }
        }
        stage ('Unit Test') {
            steps{
                sh '''
                mvn  test
                docker stop mysql
                '''
            }
        }
        stage ('SonarQube analysis') {
            steps{
                sh '''
                mvn sonar:sonar
                '''
            }
        }
        stage('Nexus'){
            steps{
                sh """mvn deploy """
            }
        }
         stage('Docker build')
        {
            steps {
                 sh 'docker build  -t ghaithbhs/devops  .'
            }
        }
	    
      stage('Push') {

			steps {
				sh 'echo $dockerhub_PSW | docker login -u ghaithbhs -p dckr_pat_PvFfLE0rm--tKJiRL1igKeLc2fQ'
				sh 'docker push ghaithbhs/devops'
			}
		}
        
       stage('Run app With DockerCompose') {
              steps {
                sh '''
                sudo docker pull ghaithbhs/devops
                sudo docker pull ghaithbhs/achat_front
		'''
              }
              }
	     
        stage('Sending email'){
           steps {
            mail bcc: '', body: '''Hello from Jenkins,
            Devops Pipeline returned success.
            Best Regards''', cc: '', from: '', replyTo: '', subject: 'Devops Pipeline', to: 'ghaith.belhadjsghaier@esprit.tn'
            }
       }

    }
}
