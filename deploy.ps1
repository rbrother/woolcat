npm install
Remove-Item -Recurse -Force .\resources\public\js\compiled
npm run release
aws s3 cp .\resources\public  s3://woolcat/ --recursive --cache-control "no-cache, no-store, must-revalidate"
# Deploys to: http://woolcat.fi.s3-website.eu-north-1.amazonaws.com/
# Mapped to Cloudfront disribution: https://d2l603lgj6s6a9.cloudfront.net/
