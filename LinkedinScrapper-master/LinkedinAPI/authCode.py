from linkedin import linkedin
import clipboard

API_KEY = '81ao9lzlr0n3ac'
API_SECRET = 'OgrjQtePx7IMGDHr'
RETURN_URL = 'https://technolife.auth0.com/login/callback'

authentication = linkedin.LinkedInAuthentication(API_KEY, API_SECRET, RETURN_URL, linkedin.PERMISSIONS.enums.values())
print(authentication.authorization_url )
f=open("auth.txt","w")
f.write(authentication.authorization_url)
clipboard.copy(authentication.authorization_url)
print ("\nCopied authorization code to clipboard...")
application = linkedin.LinkedInApplication(authentication)
