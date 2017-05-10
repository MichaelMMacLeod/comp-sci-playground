main :: IO ()
main = print "hello world"

act :: Double -> Double
act x = 1 / (1 + exp 1 ** (-x))

-- The values of each neuron in a neural network created with the given inputs,
-- weights, and activation function, fn. The size and number of layers is
-- determined by the dimensions of the weights list.
network :: [Double] -> [[[Double]]] -> (Double -> Double) -> [[Double]]
network _ [] _ = []
network inputs (w:weights) fn =
    let current = layer inputs w fn
        others  = network current weights fn
        in current : others
    where
        layer :: [Double] -> [[Double]] -> (Double -> Double) -> [Double]
        layer _ [] _ = []
        layer inputs (w:weights) fn =
            let current = neuron inputs w fn
                others  = layer inputs weights fn
                in current : others
            where
                neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
                neuron inputs weights fn = fn (sum (zipWith (*) inputs weights))

-- The errors of each weight in the output layer of a neural network. The
-- targets are the values that the network is trying to achieve. The function
-- fn' is the derivative of the function used to create the network.
outputErrors :: [Double] -> [Double] -> (Double -> Double) -> [Double]
outputErrors _ [] _ = []
outputErrors (t:targets) (o:outputs) fn' =
    let current = (t - o) * fn' o
        others  = outputErrors targets outputs fn'
        in current : others

-- The errors of each weight in a neural network created with the given inputs
-- and weights. The errors are the errors in the output layer of the network.
-- The function fn' is the derivateve of the function used to create the
-- network. The value learning_rate is a small constant which controls the speed
-- of learning.
networkErrors :: [[Double]] -- does not include the output layer
              -> [Double]
              -> [[[Double]]]
              -> [Double]
              -> (Double -> Double)
              -> Double
              -> [[[Double]]]
networkErrors [] _ _ _ _ _ = []
networkErrors network inputs weights errors fn' learning_rate =
    let
        current = layerErrors (last weights) errors fn' (last network)
        in
    where
        layerErrors weights errors fn' network =
            let
                current = neuronError (last weights)
