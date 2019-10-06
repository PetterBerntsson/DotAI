# DotAI
A test of moving "Dots" to a goal using evolution


Each "animal" is called a Dot. A Dot has a Brain which has a genome that decides its moves - The Dot gets a move-set from it's Brain in the beginning. The Mutation Chamber creates a new genome or mutates existing Brains.

The Herder controls the herd of Dots, for simplicity. It also creates new Dots after the culling the Evaluator performs each generation. The Mutation Chamber clones the most successful Brains, those decided fit by the Evaluator, saves these Brains for the next generation and derives new mutated Brains from these.

The DotAI main-class paints the dots and iterates each generation. In the DotAI the mutationProbability and the harshness (how big of a chunk will be culled each generation) are defined.

The Evaluator decides the fitness score for each Dot, which is the squared inverse of the distance to the goal.
