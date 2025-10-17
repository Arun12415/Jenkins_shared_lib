// vars/gitSafePush.groovy
def call(Map config = [:]) {
    def branch = config.branch ?: 'main'
    def message = config.message ?: 'Automated update from Jenkins'
    def repoUrl = config.repoUrl ?: error("repoUrl is required")

    echo "Configuring Git user..."
    sh """
        git config --global user.name "Jenkins"
        git config --global user.email "jenkins@server"
    """

    echo "Ensuring branch '${branch}'..."
    sh "git checkout ${branch} || git checkout -b ${branch} origin/${branch}"

    // Step 1: Commit any unstaged changes before rebase
    echo "Committing local changes if any..."
    sh "git add ."
    sh "git commit -m '${message}' || echo 'No changes to commit'"

    // Step 2: Fetch remote and rebase
    echo "Fetching remote changes and rebasing..."
    sh """
        git fetch origin ${branch}
        git rebase origin/${branch} || git rebase --abort
    """

    // Step 3: Push
    echo "Pushing changes to GitHub..."
    sh "git push https://${GIT_USERNAME}:${GIT_PASSWORD}@${repoUrl} ${branch}"

    echo "✅ Git safe push completed successfully!"
}

}
