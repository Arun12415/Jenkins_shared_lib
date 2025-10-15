def call(String repoUrl, String branch = 'main') {
    checkout([
        $class: 'GitSCM',
        branches: [[name: "refs/heads/${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'WipeWorkspace']],  // optional: clean before checkout
        userRemoteConfigs: [[url: repoUrl]]
    ])
}
