job('nodejs_docker_example') {
    scm {
        git('git://github.com/mohamedBENKHOUYA/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
//     wrappers {
//         nodejs('nodejs') // this is the name of the NodeJS installation in 
//                          // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
//     }
    steps {
        dockerBuildAndPublish {
            repositoryName('mo29172/nodejs_docker_example')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('DOCKER_HUB_ID')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
