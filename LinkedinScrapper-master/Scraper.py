import selenium.webdriver
import time
from os import environ
from abc import abstractmethod
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By


class Scraper(object):
    """
    Wrapper for selenium Chrome driver with methods to scroll through a page and
    to scrape and parse info from a linkedin page

    Params:
        - cookie {str}: li_at session cookie required to scrape linkedin profiles
        - driver {webdriver}: driver to be used for scraping
        - scroll_pause {float}: amount of time to pause (s) while incrementally
        scrolling through the page
        - scroll_increment {int}: pixel increment for scrolling
        - timeout {float}: time to wait for page to load first batch of async content
    """

    def __init__(self, cookie=None, scraperInstance=None, driver=selenium.webdriver.Chrome, driver_options={}, scroll_pause=0.1, scroll_increment=300, timeout=10):
        if type(self) is Scraper:
            raise Exception(
                'Scraper is an abstract class and cannot be instantiated directly')

        if scraperInstance:
            self.was_passed_instance = True
            self.driver = scraperInstance.driver
            self.scroll_increment = scraperInstance.scroll_increment
            self.timeout = scraperInstance.timeout
            self.scroll_pause = scraperInstance.scroll_pause
            return

        self.was_passed_instance = False

        cookie = 'AQEDASlFklsB5kDDAAABZvcoFVYAAAFnGzSZVlEAEaf3Lok9YrFocQxqsH2uRtnFIKS64MAa9ZYvwlPnyDyGMFg7omzcS3wlV1cUtmONeXygW_935TIyg-Hb9-EaAA4zWJknqZ6U-sKIUrC_c7cIU18e'
        self.driver = driver(**driver_options)
        self.scroll_pause = scroll_pause
        self.scroll_increment = scroll_increment
        self.timeout = timeout
        self.driver.get('http://www.linkedin.com')
        self.driver.set_window_size(1200, 1000)
        self.driver.add_cookie({
            'name': 'li_at',
            'value': cookie,
            'domain': '.linkedin.com'
        })

    @abstractmethod
    def scrape(self):
        raise Exception('Must override abstract method scrape')

    def get_html(self, url):
        self.load_profile_page(url)
        return self.driver.page_source

    def scroll_to_bottom(self):
        """Scroll to the bottom of the page

        Params:
            - scroll_pause_time {float}: time to wait (s) between page scroll increments
            - scroll_increment {int}: increment size of page scrolls (pixels)
        """
        expandable_button_selectors = [
            'button[aria-expanded="false"].pv-skills-section__additional-skills',
            'button[aria-expanded="false"].pv-profile-section__see-more-inline',
            'button[aria-expanded="false"].pv-top-card-section__summary-toggle-button',
            'button[data-control-name="contact_see_more"]'
        ]

        current_height = 0
        while True:
            for name in expandable_button_selectors:
                try:
                    self.driver.find_element_by_css_selector(name).click()
                except:
                    pass
            # Scroll down to bottom
            new_height = self.driver.execute_script(
                "return Math.min({}, document.body.scrollHeight)".format(current_height + self.scroll_increment))
            if (new_height == current_height):
                break
            self.driver.execute_script(
                "window.scrollTo(0, Math.min({}, document.body.scrollHeight));".format(new_height))
            current_height = new_height
            # Wait to load page
            time.sleep(self.scroll_pause)

    def wait(self, condition):
        return WebDriverWait(self.driver, self.timeout).until(condition)

    def wait_for_el(self, selector):
        return self.wait(EC.presence_of_element_located((
            By.CSS_SELECTOR, selector
        )))

    def __enter__(self):
        return self

    def __exit__(self, *args, **kwargs):
        self.quit()

    def quit(self):
        if self.driver and not self.was_passed_instance:
            self.driver.quit()
