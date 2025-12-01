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
