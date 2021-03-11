package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        int length = adjacencyMatrix.length;
        boolean[] isVisited = new boolean[length];
        ArrayList<Integer> visitedVertex = new ArrayList<>();
        ArrayDeque<Integer> vertexDeque = new ArrayDeque<>();
        vertexDeque.add(startIndex);
        visitedVertex.add(startIndex);
        for (int i = 1; i < length + 1; i++) {
            startIndex = vertexDeque.peekFirst();
            isVisited[startIndex - 1] = true;
            for (int j = 1; j < length + 1; j++) {
                if (adjacencyMatrix[startIndex - 1][j - 1]) {
                    if (!isVisited[j - 1]) {
                        vertexDeque.add(j);
                        visitedVertex.add(j);
                        isVisited[j - 1] = true;
                    }
                }
            }
            vertexDeque.removeFirst();
        }
        return visitedVertex.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {
        if (s == null) {
            return false;
        }
        String openingBrackets = "([{";
        String closingBrackets = "]})";
        ArrayDeque<Character> stackOfOpeningBrackets = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            String str = Character.toString(bracket);
            int AsciOpeningBracketForThisBracket = bracket;
            if (openingBrackets.contains(str)) {
                stackOfOpeningBrackets.push(bracket);
            } else {
                if (AsciOpeningBracketForThisBracket > 41) {
                    AsciOpeningBracketForThisBracket -= 2;
                } else {
                    AsciOpeningBracketForThisBracket -= 1;
                }
            }
            if (closingBrackets.contains(str) && stackOfOpeningBrackets.isEmpty()) {
                return false;
            }
            if (closingBrackets.contains(str) && (int) stackOfOpeningBrackets.peek() == AsciOpeningBracketForThisBracket) {
                stackOfOpeningBrackets.pop();
            }
        }
        return stackOfOpeningBrackets.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<Long> dequeOfNumbers = new ArrayDeque<>();
        ArrayDeque<String> dequeOfOperators = new ArrayDeque<>();
        String[] mass = s.split(" ");
        for (int i = 0; i < mass.length/2+1; i++) {
            try {
                dequeOfNumbers.push(Long.parseLong(mass[i]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = dequeOfNumbers.size(); i < mass.length; i++) {
            if ("(+-*/)".contains(mass[i])){
                dequeOfOperators.push(mass[i]);
            }else{
                throw new IllegalArgumentException();
            }
        }
        for (int j = 0; j < mass.length/2; j++) {
            switch (dequeOfOperators.removeLast()) {
                case "+":
                    dequeOfNumbers.push(dequeOfNumbers.pop() + dequeOfNumbers.pop());
                    break;
                case "-":
                    dequeOfNumbers.push(dequeOfNumbers.pop() - dequeOfNumbers.pop());
                    break;
                case "/":
                    dequeOfNumbers.push(dequeOfNumbers.pop() / dequeOfNumbers.pop());
                    break;
                case "*":
                    dequeOfNumbers.push(dequeOfNumbers.pop() * dequeOfNumbers.pop());
                    break;
            }
        }
        return dequeOfNumbers.peek();
    }
}
