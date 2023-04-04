job('NodeJS Docker example') {
    scm {
        git('https://github.com/hila5/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('hila')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('hila5/jenkinslab')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('hila_dockerhub')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

