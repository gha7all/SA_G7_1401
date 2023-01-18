import networkx as nx

G = nx.read_weighted_edgelist('e.txt', delimiter=" ")
print(G)