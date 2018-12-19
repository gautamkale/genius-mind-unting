import sys
import time
import random
import argparse

from selenium import webdriver
from selenium.webdriver.support.ui import Select, WebDriverWait
from selenium.common.exceptions import NoSuchFrameException
from selenium.webdriver.common.keys import Keys
import urllib.parse


def start_browser():
    br = webdriver.Firefox(executable_path='B:\geckodriver-v0.23.0-win64\geckodriver.exe')
    br.implicitly_wait(10)
    return br

def get_ua():
    ua_list = ['Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36',
               'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36',
               'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/537.75.14',
               'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0',
               'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36',
               'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0']
    ua = random.choice(ua_list)
    return ua

def scrape_results(br):
    links = br.find_elements_by_xpath("//a[@href]")
    results = []
    for link in links:
        if "linkedin.com/" in link.text:
            title = link.text
            url = link.get_attribute('href')
            title_url = (title, url)
            results.append(title_url)


    return results

def go_to_page(br, search_term, b):
    s = urllib.parse.quote(b + " " + search_term.decode("utf-8"))
    url = 'https://www.google.com/webhp?#num=1&start=0'+'&q='+s+ ' site:linkedin.com/ '
   ## urllib.quote(url)
    print(url)
    br.get(url)
    time.sleep(5)

