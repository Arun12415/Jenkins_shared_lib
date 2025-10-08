def call(){
  dependencyCheck additionalArguments: '--scan ./ --disableAssembly', odcInstallation: 'OWASP'
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
