npm install
Remove-Item -Recurse -Force .\resources\public\js\compiled
npm run release
aws s3 cp .\resources\public  s3://woolcat/ --recursive
# Deploys to: http://woolcat.s3-website.eu-north-1.amazonaws.com/