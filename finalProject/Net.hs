main :: IO ()
main = print "hello world"

act :: Double -> Double
act x = 1 / (1 + exp 1 ** (-x))

act' :: Double -> Double
act' x = x * (1 - x)

myInputs = [1,1]
myWeights = [[[0.5,0.6]],[[0.7]]]
myTargets = [1]

myNet = network myInputs myWeights act
myErrors = networkError myWeights (init myNet) (outputErrors myTargets (last myNet) act') act'

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

networkError :: [[[Double]]] -> [[Double]] -> [Double] -> (Double -> Double) -> [[Double]]
networkError [] _ _ _ = []
networkError _ [] _ _ = []
networkError _ _ [] _ = []
networkError weights hiddenLayers errors fn' =
    let current = layerError (last weights) (last hiddenLayers) errors fn'
        others  = networkError (init weights) (init hiddenLayers) current fn'
        in current : others

layerError :: [[Double]] -> [Double] -> [Double] -> (Double -> Double) -> [Double]
layerError [] _ _ _ = []
layerError _ [] _ _ = []
layerError _ _ [] _ = []
layerError (w:weights) inputs (e:errors) fn' =
    let
        current = neuronError w inputs e fn'
        others  = layerError weights inputs errors fn'
        in current : others

neuronError :: [Double] -> [Double] -> Double -> (Double -> Double) -> Double
neuronError weights inputs err fn' = sum $ zipWith (*) (map (err *) weights) (map fn' inputs)