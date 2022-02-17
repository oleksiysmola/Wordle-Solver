# Worldle Solver
This project contains my attempts to develop a Wordle
solver based on concepts in information theory
utilised in a YouTube video by 3Blue1Brown
- Link: https://www.youtube.com/watch?v=v68zYyaEmEA
- Possible Words: https://gist.github.com/Brystephor/c7fde59e673534dcb4d687243195b544
## How to use
The Wordle solver is run through the Main java class.
Note that the system only suggests guesses; it does
not play the game for you. A command line interface
prompts the user to input various responses that 
indicate their guesses and the patterns they find while
playing the game. Based on these prompts one should answer 
as follows:
- Y/N: Y for if you wish to proceed or N if you wish to abort
- What word did you guess? This should be the five letter word
you just entered into Wordle
- What pattern did you obtain? If the below pattern is obtained
from a guess then the input should be "f,1 o,2 d,0 s,0 s4,1".
In general this denotes first the letter then how many times it
occurs in the answer. Here f occurs once so f,1 represents this,
the two occurrences of o are denoted by o,2 and the lack of any d 
in the answer is indicated by d,0. The letter s appears but is 
position dependent so we give it an overall count of zero in this case
s,0. We treat greens separately and the letter is proceeded by 
the index denoting its position in the word. Here s4,1 
specifies the existence of an s specifically at index 4
in the answer.
  ![alt Example of possible Wordle pattern](patternexample1.png "Title")
For the second example, we consider instead case where the second
o is highlighted green. The input pattern would then be
"f,1 o,1 d,0 s,0 o2,1 s4,1".
  ![alt Example of possible Wordle pattern](patternexample2.png "Title")
## Log of Scores
Here is a log of the scores that the solver would have gotten
on a given day:
- 16/02/2022: 4 attempts to guess "caulk"
- 17/02/2022: 5 attempts to guess "shake"