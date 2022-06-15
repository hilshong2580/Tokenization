# Tokenization

In this project, The program will read the eBook of Moby-Dick line by line. In this step, the program will convert all letters to lower case before storing them in ArrayList.

Then process the Abbreviations check, using the regular expression of “\b(?:[a-z]\.){2,}” to check the String. Remove the dots if match the case. It should be a word boundary, that matches the situation of a character a-z following with a dot and this situation continuously appears at least 2 and unlimited times. Save the matching words into a string variable, and replace the match words with the remove dot’s token.

After the abbreviations, the program will remove the Punctuation. Using the \\p{{Punct} that prof wrote in class. Replace all punctuation with space. In addition, I will use the regular expression of \\s+ to remove the extra space between the words in lines. Also, Split the string of line by space, then save the word into the String array. Finally, add the tokens into an ArrayList if the string is not empty.

For the Stopword check, the program reads the stopword.txt and adds the content into ArrayList. Using the List function of contains to check the stop-word. Write a for loop that checks all the tokens by containing functions. If the token does not exist on the stop-word list, add it to an ArrayList. Then return the result and update the ArrayList.

For the Stemming, the program will loop to do the stemming for all tokens based on Rules:
● If the token end with us or ss, do nothing and return the token.
● Else if the token is end with sses, replace the sses by ss. Return the updated token.
● Else if the token end with ied or ies, remove ed or es from the last two character, or remove s or d at the last characters if the token’s length is 4.
● Else if the token end with s and is larger than one, create a String variable to save the origin token.
● Remove the s, then check the last character is vowel. Return the token without s if the not end with vowel.
● If end with vowel, remove the end vowel and check the token. If exist another vowel that does not follow s. Return the origin token if the does exists another vowel, else return the token without s.
● If the token end with eed or eedly, return the token that removes the d or dly if matching the case that the part of the word after the first non-vowel following a vowel.
● Else if the token end with ed, edly, ing, or ingly, create a String variable to save the origin token. Remove the matching end characters then check the token is existed vowel. Return the origin token if does not contain vowel.
● Else, add e if the update token is end with at, bl, iz or the token’s length is lower than 4, or remove one more character if the word end with the double letter which is not ll, ss or zz.
