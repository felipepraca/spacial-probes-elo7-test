#Spacial Probes

Esse software é a "resposta" para o teste da Elo7 (https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c)


#Serviços

1. Cadastrar uma missão espacial da seguinte forma

    **HTTP** : POST
  
    **URL** : http://localhost:8090/probe/mission

    **BODY** : 
    ```json
      {
      	"plateau" : {
      	    "area" : {
      		    "x" : 5,
      		    "y" : 5
      	    }
      	},
      	"probes" : [
      		{
      		    "id" : 1,
      			"direction" : "N",
      		    "coordinate" : {
      			    "x" : 1,
      			    "y" : 2
      		    }
      		},
      		{
      		    "id" : 2,
      			"direction" : "E",
      		    "coordinate" : {
      			    "x" : 3,
      			    "y" : 3
      		    }
      		}
      	],
      	"actions" : {
      		"1" : [ "L", "M" ,"L", "M", "L", "M", "L", "M", "M" ],
      		"2" : [ "M", "M", "R", "M", "M", "R", "M", "R", "R", "M" ]
      	}
      }
    ```
  
2. Atualizar a navegação de apenas uma sonda
   
    **HTTP** : PUT
   
    **URL** : http://localhost:8090/probe/1
   
    **BODY** :
    ```json
      [ "L", "M" ,"L", "M", "L", "M", "L", "M", "M" ]
    ```

3. Obter infomações de uma sonda

    **HTTP** : GET
    
    **URL** : http://localhost:8090/probe/1
    
4. Remover uma sonda

    **HTTP** : DELETE
    
    **URL** : http://localhost:8090/probe/1
