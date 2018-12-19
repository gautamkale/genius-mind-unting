from difflib import get_close_matches
from fetchInput import fetch
from searchController import *

from click import ClickException
from ProfileScraper import ProfileScraper
from Profile import Profile
from pprint import pprint
import json
import os
from selenium.webdriver import Firefox
from selenium.webdriver import Chrome
from ExportData import exportData
from CompanyScraper import CompanyScraper
import re



def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('inputfile', help="the excel file containing the input search data")
    return parser.parse_args()

def urlMatch(titles_urls, name):
    links = []
    titles= []
   ## p = re.compile('((http(s?)://)*([www])*\.|[linkedin])[linkedin/~\-]+\.[a-zA-Z0-9/~\-_,&=\?\.;]+[^\.,\s<]')
    for item in titles_urls:
      ##  print('url match-----'+ item[1])
        if ( 'linkedin.com/'  in  item[1]) :
            titles.append(item[0].split('|')[0].strip())
            links.append(item[1])
    if titles == []: return None
    elif len(titles)==1: return links[0]
    else:
        best_match = get_close_matches(name,titles,n=1)
    print(titles)
    print( best_match)
    return links

            
def main():
    args = parse_args()
    br = start_browser()
    #searchList,names = fetch(args.inputfile)
    profile_urls = []

    # for index, query in enumerate(searchList):
    #     go_to_page(br, query,names[index])
    #     titles_urls = scrape_results(br)
    #     if titles_urls == []:
    #         profile_urls.append(None)
    #     else:
    #         best_match = urlMatch (titles_urls, names[index])
    #         profile_urls.append(best_match)
    with open('Profile.json') as f:
        profile_urls = json.load(f)
    finalRs=[];
    companyProfile=[]
    for i in profile_urls:
        print('[+] -----  ' + str(i) )
        ## os.system("cli.py --url "+str(i))
        if i is  None:
            finalRs.append('No profile found')
            continue
        if isinstance(i ,(list,)):
            for j in i:
                if "linkedin.com/" in j:
                    try:
                        output1=scrape(j,None,None,None,None,'Firefox');
                        finalRs.append(output1)

                    except ValueError as Argument:
                        print("You can't divide by zero, you're silly.", Argument)
                    except Exception as Argument:
                        print ("The argument does not contain numbers\n", Argument)


        else:
             if "linkedin.com" in i:
                 try:
                     output1 = scrape(i, None, None, None, None, 'Firefox');
                     finalRs.append(output1)
                 except ValueError as Argument:
                     print("You can't divide by zero, you're silly.", Argument)
                 except Exception as Argument:
                     print("The argument does not contain numbers\n", Argument)

    # with CompanyScraper() as scraper:
    #     company = scraper.scrape(company='facebook')
    #     print(company.to_dict())
    #     companyProfile.append(company.to_dict())

    with open('profileScraped.json','w') as outputfile:
        json.dump(finalRs,outputfile)

        with open('Profile.json', 'w') as outputfile:
            json.dump(profile_urls,outputfile )


def scrape(url, user, attribute, input_file, output_file, driver):
    print('[+]++++  ' + url)
    LI_AT = 'AQEDASlFklsB5kDDAAABZvcoFVYAAAFnSeKi9FEAesa_Tj-TWuZ2WuGzwt4_BN98S7BGL6E-LrynzxSbHHNg5Oxl-VovpwlN-7fN7hSfNmyqWZLtaBUY7QDzC-TeB7tjNkqbMAuvqUoJy6lJga1dgo8c'
    if user:
        url = ''
    if (url and input_file) or (not url and not input_file):
        raise ClickException(
            'Must pass either a url or file path, but not both.')
    elif url:
       ## if 'LI_AT' not in os.environ:
         ##   raise ClickException("Must set LI_AT environment variable")
        if driver == 'Firefox':
            with ProfileScraper(driver=Firefox, cookie=LI_AT) as scraper:
                profile = scraper.scrape(url=url)


        else:
            with ProfileScraper(driver=Chrome, cookie=LI_AT) as scraper:
                profile = scraper.scrape(url=url)
    else:
        with open(input_file, 'r') as html:
            profile = Profile(html)


    if attribute:
        output = profile.__getattribute__(attribute)
    else:
        output = profile.to_dict()

    if output_file:
        with open(output_file, 'w') as outfile:
           json.dump(output, outfile)

       ## exportData(output)
    # else:
    #     pprint(output)
    return  output

if __name__ == "__main__":
    main()

    
    
    
    
    
