/****
 * Problem Description
You are given two strings, a main string S, and a pattern P. You have to find the starting indices of the anagrams of P in S.
Anagrams are permutations of a string. For P="abc”, its anagrams are abc,acb,bca,bac,cba,cab.

Note that indexing starts at 0.

Input format
There is one line of input, containing two space-separated strings S and P.

Output format
First-line should contain the number of such starting indices.
Next line should contain the indices in increasing order separated by a space.

Sample Input 1
aaba ab
Sample Output 1
2
1 2

Explanation 1
The anagrams of pattern "ab" are “ab” and “ba”. These are present at indices 1 and 2 of the input string “aaba”.
Sample Input 2
bacdgabcda abcd

Sample Output 2
3
0 5 6

Explanation 2
The anagrams of "abcd" can be seen as “bacd” at index 0, “abcd” at index 5 and “bcda” at index 6.

Constraints
1 <= length(S), length(P) <= 10^6
 */




function compareMaps(map1, map2) {
    if (map1.size !== map2.size) {
        return false;
    }

    for (let [key, value] of map1) {
        if (map2.get(key) != value) {
            return false;
        }
    }
    return true;
}

function findAllAnagramsInAString(s, p) {
    let patMap = new Map();//patter window
    for (let i = 0; i < p.length; i++){
        patMap.set(p[i], (patMap.get(p[i]) || 0) + 1);
    }

    let winMap = new Map();//sliding window
    let i = 0, j = 0; //for the sliding window
    let ans = []; //store the starting indices of window
    while (j < s.length) {
        winMap.set(s[j], (winMap.get(s[j]) || 0) + 1);//add right most ch to window
        if (j - i + 1 == p.length) {
            if (compareMaps(winMap, patMap))//if the maps are equal
                ans.push(i);//push the element
            //now shrink the winow
            winMap.set(s[i], winMap.get(s[i]) - 1);//reduce the window size
            if (winMap.get(s[i]) == 0)//check if count of ele has become zero
                winMap.delete(s[i]);
            i++;
        }
        j++;
    }
    return ans;
}



let s = 'bacdgabcda';
let p = 'abcd';
console.log(findAllAnagramsInAString(s, p));
let result = findAllAnagramsInAString(s, p);

console.log(result.length);