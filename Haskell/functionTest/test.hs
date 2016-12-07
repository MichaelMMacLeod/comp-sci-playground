fac 0 = 1
fac n = n * fac (n - 1)
 
main = do putStrLn "What is 5! ?"
          x <- readLn
          if x == fac 5
            then putStrLn "You're right!"
            else putStrLn "You're wrong!"