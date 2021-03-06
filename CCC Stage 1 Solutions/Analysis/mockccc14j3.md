This is a relatively simple math problem. First, it is important to realize that on a clock, the minute hand moves relative to the 
seconds hand (its angle between the current minute and the next minute is proportional to the number seconds that has elapsed since the 
start of the minute). Likewise, the hour hand's angle is dependent on both the minute and second hands.

To determine the number of hours, we divide the angle H by 360 degrees to get a value in the range [0, 1) 
(including 0 but not including 1), representing its progress during a full rotation around the clock. 
Then we multiply it by 12 to get a value in the range [0, 12), scaling it up to the number of hours. We don't care about 
the decimal part of this result, which represents the contribution of the minute and second hands, 
since we're finding only the hour. Thus, we can freely floor the result into an integer hour. 
If the result is 0, we must change it to 12, since that's what the output requires.

To determine the number of minutes, we first convert the input angle to an absolute angle past the 12 o'clock mark by adding H to it, 
thus shifting it clockwise by H degrees. Once again, we divide the angle by 360 degrees and multiply it by 60 minutes per hour. 
Finally, just in case we've initially shifted the angle past the 12 o'clock point, we modulo our answer by 60 minutes to get a 
measurement in the range [0, 60).

To determine the number of seconds, we shift the angle S to the absolute angle past 12 o'clock by adding both M and H to it. 
We divide the angle by 360 degrees and multiply it by 60 seconds per minute. The result must be rounded, which is classically done by 
flooring 0.5 plus the value. Once again, we modulo the answer by 60.

Lastly, we print each number, separated by colons, to a field width of 2, and padded with leading zeros. 
This can be done differently depending on your programming language.

```cpp

#include <iostream>
#include <iomanip>
using namespace std;

double H, M, S;
int HH, MM, SS;

int main() {

  cin >> H >> M >> S;

  HH = (int)(H * 12 / 360);
  if (HH == 0) HH = 12;

  MM = (int)((M + H) * 60 / 360) % 60;

  SS = (int)((S + M + H) * 60 / 360 + 0.5) % 60;

  cout << setfill('0') << setw(2) << HH << ":";
  cout << setfill('0') << setw(2) << MM << ":";
  cout << setfill('0') << setw(2) << SS << endl;

  return 0;
}
```
