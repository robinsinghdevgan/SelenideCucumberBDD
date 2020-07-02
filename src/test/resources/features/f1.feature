Feature: Searching an item and adding to cart

  Scenario Outline: User can search "<item>" and add it to cart
    Given user's browser has no cookies of visiting Flipkart
    When user visits Flipkart website
    Then user see's a login form window
    And  user can close this form by clicking on cross button

    When user clicks on search bar
    And enter required "<item>"
    And press return key
    Then search results matching the search keyword is returned

    Given user is on search results screen
    When user clicks on "<sortBy>"
    Then search results are appear in the selected sort type

    When user scrolls down to Search Brand panel
    And user user clicks on More button inside Search Brand panel
    Then brand search window opens

    When brand search window opens
    And user enters "<brand>" in search bar and presses return key
    Then user selects "<brand>" by clicking the first enlisted checkbox
    And closes the search brand div by clicking on cross button
    And user sees updated search results

    When user clicks on first search result
    Then a new tab of browser is opened

    Given user switches to product details tab
    When user clicks on add to cart
    Then item gets added to cart

    Given user is on any page of Flipkart
    When user clicks on cart icon
    Then View cart page is opened

    Examples:
      | item       | sortBy | brand   |
      | shoes      | POP    | Nike    |
      | Tv         | REL    | Samsung |
      | Smartphone | NEW    | Realme  |

