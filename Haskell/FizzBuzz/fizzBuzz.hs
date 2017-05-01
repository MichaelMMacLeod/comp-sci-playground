fizzBuzz [] = []
fizzBuzz (x:xs)
    | x `mod` 15 == 0 = "FizzBuzz" : fizzBuzz xs
    | x `mod` 3 == 0  = "Fizz" : fizzBuzz xs
    | x `mod` 5 == 0  = "Buzz" : fizzBuzz xs
    | otherwise       = show x : fizzBuzz xs