# JavaProgrammingTest
Java Programming Test

### Design
Use java reflection mechanism for merge rules. In 'demo.java', you can change initialized values manually, and the program will let you selecting which fields you want to use for mapping LME and PRIME  
The program will be look like this:

```shell
Here are some initialized informations for test
|       LME     |       15-03-2018      |       17-03-2018      |       PB      |       Lead 13 March 2018      |
        |       true    |
|       PRIME   |       14-03-2018      |       18-03-2010      |       PB      |       Lead 13 March 2018      |
PB_03_2018      |       false   |
|       LME     |       11-03-2018      |       19-03-2018      |       PB      |       Lead 13 March 2018      |
        |       true    |
|       LME     |       09-03-2018      |       21-03-2018      |       PB      |       Lead 13 March 2018      |
        |       true    |
|       LME     |       16-03-2018      |       11-03-2018      |       PB      |       Lead 13 March 2018      |
        |       true    |

e.g In example, we can type 'code' for LEM and type 'exchangeCode' for PRIME :
Please type allowed field for [LME] mapping: [code,exchangeCode,market,label],
code
e.g In example, we can type 'code' for LEM and type 'exchangeCode' for PRIME :
Please type allowed field for [PRIME] mapping: [code,exchangeCode,market,label],
exchangecode
Please select a publisher [LME/PRIME] :
PRIME
Please intput an instrument code [e.g PB_03_2018] or 'exit' for cancel:
PB_03_2018
|       PRIME   |       16-03-2018      |       21-03-2018      |       PB      |       Lead 13 March 2018      |
PB_03_2018      |       false   |
```
