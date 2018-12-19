from linkedin import linkedin

API_KEY = '81ao9lzlr0n3ac'
API_SECRET = 'OgrjQtePx7IMGDHr'
RETURN_URL = 'https://technolife.auth0.com/login/callback'

authentication = linkedin.LinkedInAuthentication(API_KEY, API_SECRET, RETURN_URL, linkedin.PERMISSIONS.enums.values())
authentication.authorization_code = 'AQRySMdqMv7qAfZ5lrGxr2mDfYNvjJyY4frrzAKo_tkdtKTaVVkqCWKb5Pj4DcPsjMdx5ZPCiM-zDjoGoWw3O1mJiBONfCv9PA_-LBLUycaBR6QXIpjP0BUvvccNQK_T1p-kVBfcuuiP5NJM3E7gYoG3-_Js2euZtHJn2unSdFEM4QsEYLgkDs7rp_9GdA'
token = authentication.get_access_token()
print ("The authentication token is\n" + token[0])
print ("\nWriting authentication token into token.txt...")
f=open("token.txt","w")
f.write(str(token[0]))
application = linkedin.LinkedInApplication(token)
