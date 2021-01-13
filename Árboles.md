# Árboles

## Definición y terminología

Los árboles están compuestos de nodos donde el primer elemento no contiene antecedente y se denomina **raíz**. Cada nodo es padre e hijo de otro, a excepción de la raíz y de las hojas (nodos sin hijos). Cada secuencia de nodos formando un arco se denomina **camino**.

- Padre: u es padre de v sii u y v están directamente conectados y el nodo u ocupa el nivel superior al nodo v
- Arco: Par de nodos (u,v) donde u es el padre de v
- Camino: Secuencia de nodos con 0 o más elementos donde cada par de nodos consecutivos forman un arco
- Altura: Número de niveles del árbol

## Clasificación. Tipos de árboles

- Auténtico: Salvo las hojas, todos los nodos tienen 2 hijos
- Completo: Todos los niveles son completos, salvo el último nivel donde en caso de faltar nodos se rellenan primero los espacios de la izquierda
- Perfecto: Árboles auténticos con todas las hojas en el nivel máximo

> **Relación entre el número de Nodos y la altura en árboles binarios:**
>
> $1 + log2(n) \leq h \leq n$

## Recorridos de árboles Binarios

```haskell
-- Preorden: Raíz primer elemento, nodo izq recursivo y nodo der recursivo
preOrderB :: TreeB a -> [a]
preOrderB EmptyB = []
preOrderB (NodeB x lt rt) = [x] ++ preOrderB lt ++ preOrderB rt

-- Postorden: Nodo izq recursivo, nodo der recursivo y raíz en el último lugar
postOrderB :: TreeB a -> [a]
postOrderB EmptyB = []
postOrderB (NodeB x lt rt) = postOrderB lt ++ postOrderB rt ++ [x]

-- En orden (solo árboles ordenados BST y AVL): parte izq recursiva, raiz, parte der recursiva
inOrderB :: TreeB a -> [a]
inOrderB (NodeB x lt rt) = inOrderB lt ++ [x] ++ inOrderB rt
```



## Constructores de Árboles en Haskell

```haskell
-- Árbol simple con varias hojas
data Tree a = Empty | Node a [Tree a] deriving Show

-- Binario simple
data Tree a = Empty | Node a (Tree a) (Tree a) deriving Show

-- Montículo zurdo
data LeftWeightedTree a = Empty | Node a Int (LeftWeightedTree a) (LeftWeightedTree a)

-- Monttículo maxifóbico
data MaxiphobicHeap a = Empty | Node a Int (MaxiphobicHeap a) (MaxiphobicHeap a)

-- AVL
data AVL a = Empty | Node a Int (AVL a) (AVL a)
```

## Implementar Árboles binarios usando Arrays

![ArrayTree.png](resources/ArrayTree.png)

- Su padre está en $\frac{i-1}{2}$
- Su hijo izquierdo está en $2*i+1$
- Su hijo derecho está en $2*i+2$

## Priority Queue

Una cola común (igual que las estudiadas el tema anterior) con un enqueue que coloca los elementos en orden decreciente, es decir, dequeue sacará siempre el elemento mayor. La operación enqueue tiene complejidad O($n$), pero puede mejorarse usando árboles binarios

## Montículo binario. Propiedad del orden del montículo

Se dice que un árbol es **montículo binario** si cumple la **propiedad del orden del montículo** y es **completo**. Los nodos hijos son mayores que los nodos padre. Los nodos hijos **NO** guardan relación entre ellos. Un ejemplo sería:

![monticuloBinario.png](resources/monticuloBinario.png)

- Insertar elementos: Creamos el nodo y lo añadimos a la primera posición disponible (al ser completo, abajo lo más a la izquierda posible). A partir de ahí, vamos intercambiando el nodo con su padre en caso de que su padre sea **mayor** que el hijo.
- Mínimo: Está en la raíz del árbol. Es el primer elemento
- Eliminar el mínimo: Eliminamos la raíz y hacemos subir el hijo de menor tamaño, así sucesivamente.

## Montículos zurdos

Son un tipo de montículos binarios que mantienen un peso en el hijo izquierdo mayor o igual al peso de su hijo derecho. Utilizan una operación `merge` para la mayoría de sus operaciones (insercción , eliminación de raíz)

```haskell
merge Empty h' = h'
merge h Empty = h
merge h@(Node x  lh rh) h'@(Node x'  lh' rh')
 | x <= x'= Node x lh (merge rh h')
 | otherwise = Node x' lh' (merge h rh')
```



## Montículo Maxifóbico

Se trata de un montículo binario donde cada nodo almacena, además de sus hijos y su contenido, el tamaño del árbol que forma. Es la suma total de nodos que conforman el sub-árbol. De esta forma, los árboles representados arriba serían de tamaño 3, 3 y 5. Las operaciones de este tipo de árbol se basan en la operación merge. Toma dos árboles y los combina creando una solución

### Operación Merge

La operación **merge** comprara las raíces de ambos árboles y elige aquella que contiene el valor más pequeño y coloca su raíz como raíz del nuevo árbol. De esta forma, obtenemos 3 árboles (hijo izquierdo del nodo, hijo derecho del nodo, árbol con el nodo mayor). De estos tres elegimos aquel con el mayor peso/tamaño y lo colocamos como uno de los hijos del nuevo árbol. Por último, el otro hijo va a ser la llamada recursiva a merge con los dos árboles que nos han quedado. La operación merge tiene complejidad O($log(n)$)

- Para añadir elementos se hace merge del nuevo nodo creado y el resto del árbol
- Para eliminar la raíz, hacemos merge de los dos nodos hijo

```haskell
merge :: (Ord a) => MaxiphobicHeap a -> MaxiphobicHeap a -> MaxiphobicHeap a
merge h1 Empty = h1
merge Empty h2 = h2
merge h1@(Node a x ai ad) h2@(Node b y bi bd) 
  | a < b = combine a ai ad h2
  | otherwise = combine b h1 bi bd
    where 
        combine elem a b c = Node elem (size a + size b + size c + 1) (primero) (merge segundo tercero)
            where (primero , segundo , tercero) = sort3 a b c
            
sort3 :: MaxiphobicHeap a -> MaxiphobicHeap a -> MaxiphobicHeap a -> (MaxiphobicHeap a, MaxiphobicHeap a, MaxiphobicHeap a)
sort3 x y z 
 | size x < size y = sort3 y x z
 | size x < size z = sort3 z y x
 | otherwise = (x , y , z)
```



## Árboles Binarios de Búsqueda (BST)

Se caracteriza por la relación de los nodos y su padre: el nodo izquierdo es menor que la raíz y esta a su vez es menor que el nodo derecho. De esta forma, mantenemos el orden del árbol. En Haskell se implementa como cualquier otro árbol

- Insertar: Comparamos recursivamente desde la raíz hasta las hojas, buscando entre qué nodos debe de situarse el nuevo elemento. En caso de encontrar el mismo elemento o bien se reemplaza o se deja como está. Si se desplaza algún nodo, será necesario repetir la operación hasta el final. La inserción de elementos tiene **O($n$)**, ya que el árbol puede no ser equilibrado
- Eliminar: Sustituimos el nodo a eliminar por el máximo de su nodo izquierdo (mayor mas pequeño) o bien por el mínimo de su nodo derecho (menor más grande). Continuamos borrando de forma recursiva. La eliminación de elementos tiene **O($n$)**, ya que el árbol puede no ser equilibrado
- Elemento máximo/mínimo: El máximo está en la hoja más a la derecha y el mínimo en la hoja más a la izquierda

## Árboles AVL

Para evitar trabajar con árboles degenerados (cuya complejidad es de **O($n$)**) usamos  algoritmos para crear árboles balanceados. Los árboles AVL son árboles binarios de búsqueda que satisfacen la propiedad de balanceado en altura. En cada nodo, la altura de sus hijos difieren a lo sumo en **1 nodo**.

![Ejemplos AVL](resources/AVL_examples.png)

> Altura del árbol: h = O($log_\phi(n)$)
>
> Mínimo número de nodos de altura h: 
>
> N(1) = 1 
>
> N(2) = 2 
>
> N(h) = 1 + N(h-1) + N(h-2) para h>2

Cada nodo almacena el valor contenido, la altura del nodo, su nodo izquierdo y su nodo derecho

### Operación rotación

Para garantizar que las operaciones sobre el árbol AVL son de complejidad **O($log(n)$)**, utilizamos la operación rotación izquierda o derecha.

La rotación a la izquierda y derecha son simétricas, por lo que solo explicaremos la rotación a la derecha. Consiste en desplazar los nodos exteriores como una cinta, colocando el nodo raíz como nodo hijo derecho y su nodo hizo izquierdo como raíz. El nodo nieto hijo mayor del árbol izq de raíz pasa a ser el nuevo hijo menor del árbol raíz desplazado.

![](resources/rotacion.png)

```haskell
-- Rotación derecha
rotR :: AVL a -> AVL a
rotR (Node k h (Node lk lh llt lrt) rt) = node lk llt (node k lrt rt)

-- Rotación izquierda
rotL :: AVL a -> AVL a
rotL (Node k h lt (Node rk rh rlt rrt)) = node rk (node k lt rlt) rrt

-- Comprobamos hacia donde se inclina el árbol
rightLeaning :: AVL a -> Bool
rightLeaning (Node x h lt rt) = height lt <= height rt

leftLeaning :: AVL a -> Bool
leftLeaning (Node x h lt rt) = height lt >= height rt

-- Rebalancear un árbol
balance :: a -> AVL a -> AVL a -> AVL a 
balance k lt rt
 | (lh-rh > 1) && leftLeaning lt  = rotR (node k lt rt)
 | (lh-rh > 1)                    = rotR (node k (rotL lt) rt) 
 | (rh-lh > 1) && rightLeaning rt = rotL (node k lt rt)
 | (rh-lh > 1)                    = rotL (node k lt (rotR rt)) 
 | otherwise                      = node k lt rt
   where lh = height lt
         rh = height rt

-- Insercción de elementos. Hay que balancear en el caso que los tamaños difieran
insert :: (Ord a) => a -> AVL a -> AVL a 
insert k' Empty = node k' Empty Empty 
insert k' (Node k h lt rt)
 | k’ == k   = Node k' h lt rt
 | k’ < k    = balance k (insert k' lt) rt
 | otherwise = balance k lt (insert k' rt)
 
-- Borrar elementos. Rebalancear el árbol
delete :: (Ord a) => a -> AVL a -> AVL a 
delete k' Empty = Empty
delete k' (Node k h lt rt)
 | k'==k     = combine lt rt
 | k'<k      = balance k (delete k' lt) rt
 | otherwise = balance k lt (delete k' rt)

combine :: AVL a -> AVL a -> AVL a
combine Empty rt = rt
combine lt Empty = lt
combine lt rt = balance k' lt rt'
 where (k',rt') = split rt
 
-- Elimina el mínimo elemento de un árbol
split :: AVL a -> (a,AVL a)
split (Node k h Empty rt) = (k,rt)
split (Node k h lt rt) = (k',balance k lt' rt)
 where (k',lt') = split lt

-- El resto de operaciones son iguales a un BST
```