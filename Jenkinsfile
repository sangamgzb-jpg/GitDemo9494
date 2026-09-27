pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                bat 'mvn -B test -PRegression'
            }
        }
    }
}
