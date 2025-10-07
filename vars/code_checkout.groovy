#!/usr/bin/env groovy

// vars/codeCheckout.groovy

def call(String gitUrl, String branch = 'main') {
    // Simple echo statements
    echo "================================"
    echo "Git Checkout Starting..."
    echo "Repository: ${gitUrl}"
    echo "Branch: ${branch}"
    echo "================================"
    
    // Use standard checkout step
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        userRemoteConfigs: [[url: gitUrl]]
    ])
    
    echo "✓ Git Checkout Completed Successfully!"
}
