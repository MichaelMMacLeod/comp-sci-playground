main :: IO ()
main = print "hello world"

net :: [Double] -> [[[Double]]] -> (Double -> Double) -> [[Double]]
net _ [] _ = []
net inputs (w:weights) f =
    let current = map f (layer inputs w)
        others = net . layer $ weights f
    in current : others

layer :: [Double] -> [[Double]] -> [Double]
layer _ [] = []
layer inputs (w:weights) = node inputs w : layer inputs weights

node :: [Double] -> [Double] -> Double
node inputs weights = sum (zipWith (*) inputs weights)

act :: Double -> Double
act x = 1 / (1 + exp 1 ** (-x))
