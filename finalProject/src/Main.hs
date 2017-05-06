module Main where

import qualified Data.ByteString as B
import System.Random

main :: IO ()
main = do
    imageFile <- readFile "toClassify.txt"
    weightFile <- readFile "weights.txt"
    let neuralNet = net inputs weights act
        inputs    = read imageFile
        weights   = read weightFile
    putStrLn $ "Classification: " ++ show neuralNet

randomize :: [Int] -> IO [[Double]]
randomize [] = return []
randomize (l:layers) = do
    num <- randomIO
    others <- randomize layers
    return $ replicate l num : others

-- Returns the value of each neuron in a neural network created with the
-- specified inputs, weights, and activation function, f.
net :: [Double] -> [[[Double]]] -> (Double -> Double) -> [[Double]]
net _ [] _ = []
net inputs (w:weights) f =
    let currentLayer = layer inputs w f
        nextLayers   = net currentLayer weights f
    in  currentLayer : nextLayers

-- Returns the value of each neuron in a neuron layer created with the specified
-- inputs, weights, and activation function, f.
layer :: [Double] -> [[Double]] -> (Double -> Double) -> [Double]
layer _ [] _ = []
layer inputs (w:weights) f =
    let currentNeuron = neuron inputs w f
        nextNeurons   = layer inputs weights f
    in  currentNeuron : nextNeurons

-- Returns the value of a neuron created with the specified inputs, weights, and
-- activaiton function, f.
neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = f (sum (zipWith (*) inputs weights))

-- A logistic activation function.
act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

-- The derivative of act.
act' :: Floating a => a -> a
act' n = act n * (1 - act n)

cost :: Floating a => a -> a -> a
cost target actual = (1/2) * (target - actual) ** 2
